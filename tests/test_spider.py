import unittest
from spiders.tour_spider import TourSpider

class TestTourSpider(unittest.TestCase):
    def test_fetch_data(self):
        spider = TourSpider('https://you.ctrip.com/sight/suzhou11/s0-p1.html')
        spider.fetch_data()
        # 添加断言来验证数据的正确性

if __name__ == '__main__':
    unittest.main()
