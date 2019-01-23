class BaseClass

	def initialize (browser)
		@browser = browser
	end


end

class Site < BaseClass

	def home_page
		@home_page = HomePage.new(@browser)
	end

	def login_page
		@login_page = LoginPage.new(@browser)
	end

	def account_page
		@account_page = AccountPage.new(@browser)
	end

	def restaurants_list_page
		@restaurants_list_page = RestaurantsListPage.new(@browser)
	end

	def search_page
		@search_page = SearchPage.new(@browser)
	end

	def restaurant_page
		@restaurant_page = RestaurantPage.new(@browser)
	end

	def confirmation_page
		@confirmation_page = ConfirmationPage.new(@browser)
	end

	def reservation_history_page
		@reservation_history_page = ReservationHistoryPage.new(@browser)
	end

	def close
		@browser.close
	end

end