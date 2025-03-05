import mysql.connector
import pandas as pd
from sklearn.ensemble import IsolationForest

# Connect to the MySQL database
db_connection = mysql.connector.connect(
    host="localhost",
    user="root",
    password="pass",
    database="helldivers"
)

# Query to retrieve the kill data from the database
query = """SELECT 
    (SUM(s.shots_hit)/SUM(s.shots_fired)) as accuracy, 
    COUNT(DISTINCT k.match_id) AS matchCount,
    COUNT(k.id) AS killCount
FROM 
    player p
LEFT JOIN 
    stats s ON p.id = s.player_id
LEFT JOIN 
    kill_records k ON p.id = k.player_id
WHERE
    p.id > 1
GROUP BY 
    p.id;"""

# Execute the query and fetch the results
cursor = db_connection.cursor()
cursor.execute(query)
data = cursor.fetchall()

# Convert the fetched data into a pandas DataFrame
data_df = pd.DataFrame(data, columns=['accuracy', 'matchCount', 'killCount'])

# Close the database connection
db_connection.close()

# Feature selection
X = data_df[['accuracy', 'matchCount', 'killCount']]

# Initialize and train the Isolation Forest model
model = IsolationForest(contamination=0.01)
model.fit(X)

# Predict outliers (cheaters)
predictions = model.predict(X)

# Output the predicted outliers
cheaters = data_df[predictions == -1]
print(cheaters)