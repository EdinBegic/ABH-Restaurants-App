require 'rspec'
require 'selenium-webdriver'
require 'watir'
require 'require_all'
require_all './libs'


site = Site.new(Watir::Browser.new :chrome)

describe 'ABH restaurants regression test' do

	context 'When making a reservation' do

		it 'should open ABH restaurants page' do
			site.home_page.open
		end

		it 'should go to login page' do
			site.home_page.open_login
		end

		it 'should enter the email' do
			site.login_page.enter_email 'admin@abh.com'
		end

		it 'should enter the password' do
			site.login_page.enter_password 'admin'
		end

		it 'should login to application' do
			site.login_page.login
			sleep 5
		end

		it 'should go to the Restaurants section' do
			site.home_page.go_to_restaurants
		end

		it 'should open sort section' do
			site.restaurants_list_page.sort
		end

		it 'should sort by price range ASC' do
			site.restaurants_list_page.sort_asc
			sleep 2
		end

		it 'should open filter section' do
			site.restaurants_list_page.filter
		end

		it 'should filter by price' do
			site.restaurants_list_page.filter_by_price
			sleep 2
		end

		it 'should search for a restaurant' do
			site.restaurants_list_page.search_restaurant
		end

		it 'should open the restaurants page' do
			site.restaurants_list_page.open_restaurant
		end

		it 'should clear the default date' do
			site.restaurant_page.clear_date
			sleep 2
		end

		it 'should choose the date in the past' do
			site.restaurant_page.choose_date 'December 01,2018'
			sleep 2
		end

		it 'should find the table' do
			site.restaurant_page.find_table
			sleep 2
		end

		it 'should clear the invalid date' do
			site.restaurant_page.clear_date
			sleep 3
		end

		it 'should choose the good date' do
			site.restaurant_page.choose_date 'December 02,2019'
			sleep 2
		end

		it 'should enter the time that is not valid' do
			site.restaurant_page.open_time
			site.restaurant_page.choose_time '20:33'
			sleep 2
		end

		it 'should delete time' do
			site.restaurant_page.clear_time
			sleep 1
		end

		it 'should enter valid time' do
			site.restaurant_page.choose_time '20:30'
			sleep 1
		end

		it 'should find the table' do
			site.restaurant_page.find_table
		end

		it 'should complete the reservation' do
			site.confirmation_page.complete_reservation
			sleep 2
		end
	end


	context 'when trying to make the same reservation again' do

		it 'should clear the default date' do
			site.restaurant_page.clear_date
		end

		it 'should enter the same date' do
			site.restaurant_page.choose_date 'December 02,2019'
			sleep 1
		end

		it 'should enter the same time' do
			site.restaurant_page.open_time
			site.restaurant_page.choose_time '20:30'
			site.restaurant_page.send_time
			sleep 1
		end

		it 'should find the table' do
			site.restaurant_page.find_table
			sleep 2
		end

		it 'should choose another time offered' do
			site.restaurant_page.choose_another_time
			sleep 1
		end

		it 'should complete the reservation' do
			site.confirmation_page.complete_reservation
			sleep 1
		end

	end
end