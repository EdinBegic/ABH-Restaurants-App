require 'rspec'
require 'selenium-webdriver'
require 'watir'
require 'require_all'
require_all './libs'


site = Site.new(Watir::Browser.new :chrome)

describe 'ABH restaurants regression test' do

	context 'When rating the place' do

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
		end

		it 'should go to the Restaurants section' do
			site.home_page.go_to_restaurants
		end


		it 'should open the restaurants page' do
			site.restaurants_list_page.open_restaurant
		end

		it 'should open the rating form' do
			site.restaurant_page.open_rating_form
		end

		it 'should write a review' do
			site.restaurant_page.write_review 'Good.'
			sleep 2
		end

		it 'should rate the place' do
			site.restaurant_page.rate_place
			sleep 2
		end

		it 'should send the review' do
			site.restaurant_page.send_review
			sleep 2
		end

	end
end