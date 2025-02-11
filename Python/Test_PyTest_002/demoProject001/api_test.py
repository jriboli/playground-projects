from woocommerce import API

wcapi = API(
    url="http://localhost:9090",
    consumer_key="",
    consumer_secret="",
    version="wc/v3"
)

r = wcapi.get("products")

import pprint
pprint.pprint(r.json())