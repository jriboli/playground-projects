import os

print("Step 1: Running Data Loader...")
os.system("python src/data_loader.py")

print("Step 2: Running Feature Engineering...")
os.system("python src/feature_engineering.py")

print("Step 3: Training Model...")
os.system("python src/model.py")

print("Step 4: Making Predictions...")
os.system("python src/predict.py")

print("✅ Pipeline Completed Successfully!")