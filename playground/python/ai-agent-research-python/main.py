# %%
from dotenv import load_dotenv
from pydantic import BaseModel
from langchain_openai import ChatOpenAI
from langchain_anthropic import ChatAnthropic
from langchain_core.prompts import ChatPromptTemplate
from langchain_core.output_parsers import PydanticOutputParser
from langchain.agents import create_tool_calling_agent, AgentExecutor

from tools import search_tool, wiki_tool, save_tool

load_dotenv()

llm = ChatOpenAI(model="gpt-4o-mini")
# llm2 = ChatAnthropic(model="claude-3-5-sonnet-20241022")

# BASIC CONNECTIVITY TEST
#response = llm.invoke("What is the meaning of life?")
#print(response)

class ResearchResponse(BaseModel):
    topic: str
    summary: str
    sources: list[str]
    tools_used: list[str]
    
parser = PydanticOutputParser(pydantic_object=ResearchResponse)

prompt = ChatPromptTemplate.from_messages(
    [
        (
            "system",
            """
            You are a research assistant that will help generate a research paper. 
            Answer the user query and use necessary tools. 
            Wrap the output in this format and provide no other text\n{format_instructions}
            """,
        ),
        ("placeholder", "{chat_history}"),
        ("human", "{query}"),
        ("placeholder", "{agent_scratchpad}"),
    ]
).partial(format_instructions=parser.get_format_instructions())

tools = [search_tool, wiki_tool, save_tool]
agent = create_tool_calling_agent(
    llm=llm,
    prompt=prompt,
    #tools=[]  -- Empty List
    tools=tools
)

# agent_executor = AgentExecutor(agent=agent, tools=[], verbose=True)  -- Empty Tools List
agent_executor = AgentExecutor(agent=agent, tools=tools, verbose=True)
#raw_response = agent_executor.invoke({"query": "What is the capitcal of France?"})
query = input("What can I help you research?")
raw_response = agent_executor.invoke({"query": query})

# FOR TESTING 
# raw_response = {'query': 'What is the capitcal of France?', 'output': '{"topic":"Capital of France","summary":"The capital of France is Paris.","sources":["https://en.wikipedia.org/wiki/Paris"],"tools_used":[]}`'}

print("RAW: \n", raw_response)

structured_response = parser.parse(raw_response.get("output"))
print("STRUCTURED: \n ", structured_response)

print(structured_response.topic)
print(structured_response.summary)
print(structured_response.sources)

