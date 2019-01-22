require 'require_all'
require_all './libs'

class HomePage < BaseClass
	attr_accessor :login_link
	attr_accessor :restaurants_button

	URL = "https://abh-restaurants.herokuapp.com/"

	def initialize(browser)
		super(browser)

		@login_link = @browser.a(id: 'login-link')
		@restaurants_button = @browser.link(href: "/restaurant-list")
		
	end

	def open
		@browser.goto URL
		self
	end

	def open_login
		login_link.click
	end

	def go_to_restaurants
		restaurants_button.click
	end
	
end