from flask import Flask, request, jsonify
from langchain import OpenAI, SQLDatabase
from langchain_experimental.sql import SQLDatabaseChain

db = SQLDatabase.from_uri(
    "mysql+pymysql://root:test-password@127.0.0.1/testcases")
db_chain = SQLDatabaseChain(
        llm=OpenAI(model_name="gpt-3.5-turbo", temperature=0),
        database=db,
        verbose=True)

app = Flask(__name__)


@app.route('/query', methods=['POST'])
def query():
    try:
        data = request.json
        question = data['question']

        # Process the question and generate an answer here
        answer = {'answer': db_chain.run(question)}

        return jsonify(answer), 200
    except Exception as e:
        error_response = {'error': str(e)}
        return jsonify(error_response), 400


if __name__ == '__main__':
    app.run(debug=True)
