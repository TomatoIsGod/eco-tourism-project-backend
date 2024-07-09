import requests
from bs4 import BeautifulSoup
from data.database import Sight, session

class TourSpider:
    def __init__(self, start_url):
        self.start_url = start_url

    def fetch_data(self):
        response = requests.get(self.start_url)
        if response.status_code == 200:
            soup = BeautifulSoup(response.text, 'html.parser')
            sights = self.parse_data(soup)
            self.save_data(sights)

    def parse_data(self, soup):
        sights = []
        # 解析逻辑，根据具体的 HTML 结构解析数据
        for sight in soup.select('.sight_item'):
            name = sight.select_one('.sight_name').text
            city = "苏州"
            rating = float(sight.select_one('.sight_rating').text)
            tags = ','.join(tag.text for tag in sight.select('.sight_tags'))
            popularity = float(sight.select_one('.sight_popularity').text)
            review_count = int(sight.select_one('.sight_review_count').text)
            address = sight.select_one('.sight_address').text
            ticket_price = float(sight.select_one('.sight_ticket_price').text)
            sights.append(Sight(
                name=name, city=city, rating=rating, tags=tags,
                popularity=popularity, review_count=review_count,
                address=address, ticket_price=ticket_price
            ))
        return sights

    def save_data(self, sights):
        for sight in sights:
            session.add(sight)
        session.commit()
