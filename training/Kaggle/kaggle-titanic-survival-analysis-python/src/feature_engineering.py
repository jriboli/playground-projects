import pandas as pd 


def engineer_features(df):
    df = pd.get_dummies(df, columns=['Sex', 'Embarked'], drop_first=True)
    df['FamilySize'] = df['SibSp'] + df['Parch']
    df.drop(columns=['SibSp', 'Parch', 'PassengerId', 'Name', 'Ticket'], inplace=True)
    return df

if __name__ == "__main__":
    from data_loader import load_data, clean_data

    train, test = load_data()
    train = clean_data(train)
    test = clean_data(test)

    train = engineer_features(train)
    test = engineer_features(test)

    print(train.head())  # Debugging
    print(test.head())   # Debugging
