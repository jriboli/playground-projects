import numpy as np
import scipy.stats as stats
from math import sqrt

# Question: What is the probability a player who has a 70% accuracy, shoots 500 shots and makes at leat 375 of them. 

# Step 1: Define given values
n = 500      # Total shots
p = 0.7      # Probability of making a shot
x = 375      # Target number of successful shots

# Step 2: Compute Mean and Standard Deviation
mu = n * p  # Mean (expected number of successes)
sigma = sqrt(n * p * (1 - p))  # Standard deviation

print(f"Mean (μ): {mu}")
print(f"Standard Deviation (σ): {sigma:.2f}")

# Step 3: Convert to Z-Score
z = (x - mu) / sigma
print(f"Z-Score for X = {x}: {z:.2f}")

# Step 4: Compute Probability using Normal Approximation
probability = 1 - stats.norm.cdf(z)  # P(X > 375)
print(f"Probability of making more than 375 shots: {probability:.4f} (or {probability*100:.2f}%)")

# Step 5: Visualization (Optional)
import matplotlib.pyplot as plt

# Generate X values for normal curve
x_values = np.linspace(mu - 4*sigma, mu + 4*sigma, 1000)
y_values = stats.norm.pdf(x_values, mu, sigma)

# Plot the normal curve
plt.figure(figsize=(10, 5))
plt.plot(x_values, y_values, label="Normal Approximation", color="blue")

# Shade the area greater than 375
plt.fill_between(x_values, y_values, where=(x_values >= x), color="red", alpha=0.5, label="P(X > 375)")

# Vertical line for X=375
plt.axvline(x, color='black', linestyle="dashed", label="X = 375")
plt.axvline(mu, color='green', linestyle="dashed", label="Mean (μ) = 350")

plt.title("Normal Approximation of Binomial Distribution")
plt.xlabel("Successful Shots")
plt.ylabel("Probability Density")
plt.legend()
plt.show()
