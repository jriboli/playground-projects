
import os
from dotenv import load_dotenv
from pathlib import Path

class EnvUtility:

    def __init__(self, env_file=None):
        if env_file:
            env_path = Path(env_file)
        else:
            env_path = Path('.') / '.env'

        load_dotenv(dotenv_path=env_path)

    def get_env_value(self, key, default=None):
        return os.getenv(key, default)

