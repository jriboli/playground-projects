import os
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score
import pickle


def train_model(X_train, y_train, X_test, y_test):
    model = RandomForestClassifier(n_estimators=100, random_state=42)
    model.fit(X_train, y_train)

    predictions = model.predict(X_test)
    print("Model Accuracy:", accuracy_score(y_test, predictions))

    # Ensure 'models/' directory exists
    os.makedirs("models", exist_ok=True)

    # Save the trained model
    model_path = "models/titanic_model.pkl"
    with open(model_path, "wb") as f:
        pickle.dump(model, f)

    print(f"Model saved to {model_path}")
    return model

if __name__ == "__main__":
    from data_loader import load_data, clean_data
    from feature_engineering import engineer_features

    train, test = load_data()
    train = clean_data(train)
    test = clean_data(test)

    train = engineer_features(train)
    test = engineer_features(test)

    X_train = train.drop(columns=['Survived'])
    y_train = train['Survived']

    X_test = test.drop(columns=['Survived'])
    y_test = test['Survived']

    train_model(X_train, y_train, X_test, y_test)
