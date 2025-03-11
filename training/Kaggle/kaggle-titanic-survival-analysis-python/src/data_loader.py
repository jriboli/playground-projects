import pandas as pd
from sklearn.model_selection import train_test_split


def load_data():
    df = pd.read_csv("data/tested.csv")
    train, test = train_test_split(df, test_size=0.2, random_state=42)  # 80% train, 20% test
    return train, test

def clean_data(df):
    df['Age'].fillna(df['Age'].median(), inplace=True)
    df['Embarked'].fillna(df['Embarked'].mode()[0], inplace=True)
    df.drop(columns=['Cabin'], inplace=True)
    return df

if __name__ == "__main__":
    train, test = load_data()
    train = clean_data(train)
    test = clean_data(test)
    print("Train set size:", train.shape)
    print("Test set size:", test.shape)
