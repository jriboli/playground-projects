import pickle
import pandas as pd


def load_model():
    with open("models/titanic_model.pkl", "rb") as f:
        model = pickle.load(f)
    return model

def generate_predictions(X_test, y_test):
    model = load_model()
    predictions = model.predict(X_test)

    result = pd.DataFrame({"Actual": y_test, "Predicted": predictions})
    result.to_csv("submission.csv", index=False)
    print("Predictions saved to submission.csv")

if __name__ == "__main__":
    from data_loader import load_data, clean_data
    from feature_engineering import engineer_features

    train, test = load_data()
    train = clean_data(train)
    test = clean_data(test)

    train = engineer_features(train)
    test = engineer_features(test)

    X_test = test.drop(columns=['Survived'])
    y_test = test['Survived']

    generate_predictions(X_test, y_test)
