import numpy as np
import random

# Simulations count
N = 100000

# Coin Flip – At Least One Heads
def simulate_coin_flips(n_trials=N):
    success = sum(any(random.choice(['H', 'T']) == 'H' for _ in range(3)) for _ in range(n_trials))
    return success / n_trials

# Rolling a Die – Sum of Two Rolls = 8
def simulate_dice_rolls(n_trials=N):
    success = sum(sum(random.randint(1, 6) for _ in range(2)) == 8 for _ in range(n_trials))
    return success / n_trials

# Drawing a Red Card from a Deck
def simulate_red_card(n_trials=N):
    success = sum(random.choice(["Red"] * 26 + ["Black"] * 26) == "Red" for _ in range(n_trials))
    return success / n_trials

# Drawing Two Red Marbles Without Replacement
def simulate_red_marbles(n_trials=N):
    success = 0
    for _ in range(n_trials):
        bag = ["R"] * 5 + ["B"] * 3
        drawn = random.sample(bag, 2)  # Draw two without replacement
        if drawn.count("R") == 2:
            success += 1
    return success / n_trials

# Guessing PIN "1234"
def simulate_pin_guess(n_trials=N):
    success = sum("".join(str(random.randint(0, 9)) for _ in range(4)) == "1234" for _ in range(n_trials))
    return success / n_trials

# Birthday Paradox – At Least One Birthday Match (23 people)
def simulate_birthday_paradox(n_trials=N):
    def has_duplicate_birthdays():
        birthdays = [random.randint(1, 365) for _ in range(23)]
        return len(birthdays) > len(set(birthdays))  # Check for duplicates
    
    success = sum(has_duplicate_birthdays() for _ in range(n_trials))
    return success / n_trials

# At Least One Candidate Not Hired
def simulate_candidate_hiring(n_trials=N):
    success = sum(not all(random.choices([1, 0], [1/3, 2/3], k=3)) for _ in range(n_trials))
    return success / n_trials

# Running Simulations
results = {
    "At least one heads (coin flip)": simulate_coin_flips(),
    "Rolling sum of 8 (two dice)": simulate_dice_rolls(),
    "Red card from deck": simulate_red_card(),
    "Two red marbles (no replacement)": simulate_red_marbles(),
    "Guessing PIN '1234'": simulate_pin_guess(),
    "At least one birthday match (23 students)": simulate_birthday_paradox(),
    "At least one person not hired": simulate_candidate_hiring(),
}

# Print Results
for key, value in results.items():
    print(f"{key}: {value:.4f}")
