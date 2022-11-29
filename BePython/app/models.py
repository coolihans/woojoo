from sqlalchemy import Column, VARCHAR, BigInteger, SmallInteger, Date, Float, ForeignKey, Table, Boolean
from sqlalchemy.orm import relationship
from database import Base

product_food = Table("product_food", Base.metadata,
                       Column("product_id", ForeignKey("product.id"), primary_key=True),
                       Column("food_id", ForeignKey("food.id"), primary_key=True))

product_maker = Table("product_maker", Base.metadata,
                       Column("product_id", ForeignKey("product.id"), primary_key=True),
                       Column("maker_id", ForeignKey("maker.id"), primary_key=True))

like_product = Table("like_product", Base.metadata,
                       Column("product_id", ForeignKey("product.id"), primary_key=True),
                       Column("user_id", ForeignKey("user.id"), primary_key=True))


class Product(Base):
	__tablename__ = 'product'

	id = Column(BigInteger, primary_key=True, autoincrement=True)
	image = Column(VARCHAR(2048), nullable=True)
	cutted_img = Column(VARCHAR(2048), nullable=True)
	like_num = Column(BigInteger, nullable=True)
	name = Column(VARCHAR(255), nullable=False)
	alcohol = Column(Float, nullable=False)
	anju_note = Column(VARCHAR(255), nullable=True)
	award = Column(VARCHAR(255), nullable=True)
	price = Column(BigInteger, nullable=False)
	distinction = Column(VARCHAR(255), nullable=True)
	ingredient = Column(VARCHAR(255), nullable=True)
	keyword = Column(VARCHAR(255), nullable=True)
	other_note = Column(VARCHAR(255), nullable=False)
	storage_note = Column(VARCHAR(255), nullable=False)
	subtext = Column(VARCHAR(255), nullable=False)
	taste_note = Column(VARCHAR(255), nullable=False)
	taste_standard = Column(VARCHAR(255), nullable=False)
	type = Column(VARCHAR(255), nullable=False)
	volume = Column(VARCHAR(255), nullable=False)

	acidity = Column(SmallInteger, nullable=False)
	weight = Column(SmallInteger, nullable=False)
	bitter = Column(SmallInteger, nullable=False)
	body = Column(SmallInteger, nullable=False)
	carbonic = Column(SmallInteger, nullable=False)
	flavor = Column(SmallInteger, nullable=False)
	nutty = Column(SmallInteger, nullable=False)
	plain = Column(SmallInteger, nullable=False)
	richness = Column(SmallInteger, nullable=False)
	spicy = Column(SmallInteger, nullable=False)
	sweet = Column(SmallInteger, nullable=False)
	tannin = Column(SmallInteger, nullable=False)

	fruit_category = Column(SmallInteger, nullable=True)
	flower_category = Column(SmallInteger, nullable=True)
	herb_category = Column(SmallInteger, nullable=True)
	nut_category = Column(SmallInteger, nullable=True)
	other_category = Column(SmallInteger, nullable=True)
	situation_category = Column(SmallInteger, nullable=True)
	foods = relationship("Food",
						secondary=product_food,
						back_populates="products")
	makers = relationship("Maker",
						secondary=product_maker,
						back_populates="products")
	users = relationship("User",
						secondary=like_product,
						back_populates="products")
	shops = relationship("Shop", back_populates="product", order_by="Shop.price")


class Food(Base):
	__tablename__ = 'food'

	id = Column(BigInteger, primary_key=True, autoincrement=True)
	img = Column(VARCHAR(2048), nullable=False)
	name = Column(VARCHAR(255), nullable=False)
	products = relationship("Product",
					secondary=product_food,
					back_populates="foods")


class Maker(Base):
	__tablename__ = 'maker'

	id = Column(BigInteger, primary_key=True, autoincrement=True)
	address = Column(VARCHAR(255), nullable=False)
	ceo_name = Column(VARCHAR(255), nullable=False)
	name = Column(VARCHAR(255), nullable=False)
	official_name = Column(VARCHAR(255), nullable=False)
	products = relationship("Product",
						secondary=product_maker,
						back_populates="makers")


class User(Base):
	__tablename__ = 'user'

	id = Column(BigInteger, primary_key=True, autoincrement=True)
	birthdate = Column(Date, nullable=False)
	email = Column(VARCHAR(255), nullable=False)
	gender = Column(VARCHAR(255), nullable=False)
	img = Column(VARCHAR(255), nullable=False)
	nickname = Column(VARCHAR(255), nullable=False)
	question1 = Column(SmallInteger, nullable=False)
	question2 = Column(SmallInteger, nullable=False)
	question3 = Column(SmallInteger, nullable=False)
	question4 = Column(SmallInteger, nullable=False)
	question5 = Column(SmallInteger, nullable=False)
	question6 = Column(SmallInteger, nullable=False)
	usertype = Column(VARCHAR(255), nullable=False)
	type = Column(VARCHAR(255), nullable=False)
	gosu = Column(Boolean, nullable=False)
	review_count = Column(BigInteger, nullable=True)
	products = relationship("Product",
					secondary=like_product,
					back_populates="users")


class Shop(Base):
	__tablename__ = 'shop'

	id = Column(BigInteger, primary_key=True, autoincrement=True)
	product_id = Column(BigInteger, ForeignKey("product.id"))
	image = Column(VARCHAR(500), nullable=False)
	mall_name = Column(VARCHAR(255), nullable=False)
	name = Column(VARCHAR(255), nullable=False)
	price = Column(BigInteger, nullable=False)
	url = Column(VARCHAR(500), nullable=False)
	product = relationship("Product", back_populates="shops")