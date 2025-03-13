import numpy as np

# Module 1 Quiz - See the impact of increasing all numbers in a set by a static value
# verses by a percentage value

# The calculations
def compute_statistics(data):
    """Compute Mean, Median, Standard Deviation, and IQR for a given dataset."""
    if not data:
        print("Dataset is empty. Please provide valid data.")
        return
    
    # Mean
    mean = np.mean(data)
    
    # Median
    median = np.median(data)
    
    # Standard Deviation (Population)
    std_dev = np.std(data, ddof=0)  # ddof=0 for population, use ddof=1 for sample
    
    # Interquartile Range (IQR)
    q1 = np.percentile(data, 25)  # 25th percentile (Q1)
    q3 = np.percentile(data, 75)  # 75th percentile (Q3)
    iqr = q3 - q1
    
    # Print Results
    print(f"Dataset: {data}")
    print(f"Mean: {mean}")
    print(f"Median: {median}")
    print(f"Standard Deviation: {std_dev:.2f}")
    print(f"Interquartile Range (IQR): {iqr}")
    print("-" * 40)

# The Datasets
# Original
data = [3, 28, 34, 69, 87]
# Increase by 5
databy5 = [8, 33, 39, 74, 92]
# Increase by 5%
databypercent = [4.5, 42, 51, 103.5, 130.5]

compute_statistics(data)
compute_statistics(databy5)
compute_statistics(databypercent)