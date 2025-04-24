import requests
import openai
import os


from dotenv import load_dotenv

load_dotenv()
client = OpenAI(api_key=os.getenv("OPEN_API_KEY"))

def fetch_question(difficulty="easy"):
    url = f"https://opentdb.com/api.php?amount=1&difficulty={difficulty}&type=multiple"
    res = requests.get(url).json()
    return res["results"][0]

def ask_with_llm(question, options):
    prompt = f"""Trivia Time!
Question: {question}
Options: {', '.join(options)}
Which one is correct and why?"""
    
    response = openai.ChatCompletion.create(
        model="gpt-4",
        messages=[{"role": "user", "content": prompt}]
    )
    return response['choices'][0]['message']['content']

def game_loop():
    score = 0
    difficulty = "easy"

    for round in range(5):
        data = fetch_question(difficulty)
        question = data["question"]
        correct = data["correct_answer"]
        all_options = data["incorrect_answers"] + [correct]
        random.shuffle(all_options)

        print(f"\nQ: {question}")
        for i, opt in enumerate(all_options):
            print(f"{i+1}. {opt}")

        user_choice = input("Your answer (1-4): ")
        if all_options[int(user_choice) - 1] == correct:
            print("✅ Correct!")
            score += 1
            if score >= 3:
                difficulty = "medium"
        else:
            print(f"❌ Wrong. Correct answer: {correct}")

    print(f"\nYour final score: {score}/5")

if __name__ == "__main__":
    import random
    game_loop()