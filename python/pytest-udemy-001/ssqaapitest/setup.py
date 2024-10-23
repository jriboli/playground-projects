from setuptools import setup, find_packages

setup(name='ssqaapitest',
      version='1.0',
      description='Practice API testing',
      author='Rocket Raccoon',
      packages=find_packages(),
      zip_safe=False,
      install_requires=[
            "PyMySQL==1.1.1",
            "pytest==8.3.3",
            "pytest-html==4.1.1",
            "requests==2.32.3",
            "requests-oauthlib==2.0.0",
            "WooCommerce==3.0.0",
            "python-dotenv==1.0.1"
      ]
)
