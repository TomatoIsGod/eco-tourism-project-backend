from sqlalchemy import create_engine, Column, Integer, String, Float
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from config.config import DATABASE_URI

Base = declarative_base()
engine = create_engine(DATABASE_URI)
Session = sessionmaker(bind=engine)
session = Session()

class Sight(Base):
    __tablename__ = 'sights'
    id = Column(Integer, primary_key=True)
    name = Column(String)
    city = Column(String)
    rating = Column(Float)
    tags = Column(String)
    popularity = Column(Float)
    review_count = Column(Integer)
    address = Column(String)
    ticket_price = Column(Float)

def init_db():
    Base.metadata.create_all(engine)

def get_sights():
    return session.query(Sight).all()

def get_sights_by_city(city):
    return session.query(Sight).filter(Sight.city == city).all()
