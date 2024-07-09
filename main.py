from flask import Flask, jsonify, request
from spiders.tour_spider import TourSpider
from data.database import init_db, get_sights, get_sights_by_city

app = Flask(__name__)

# 初始化数据库
init_db()

@app.route('/sights', methods=['GET'])
def sights():
    city = request.args.get('city')
    if city:
        sights = get_sights_by_city(city)
    else:
        sights = get_sights()
    return jsonify(sights)

if __name__ == '__main__':
    app.run(debug=True)
