require 'rspec'
require 'selenium-webdriver'
require 'watir'
require 'require_all'
require_all './libs'


site = Site.new(Watir::Browser.new :chrome)

describe 'ABH restaurants regression test' do

	context 'When creating an account' do


		it 'should open ABH restaurants page' do
			site.home_page.open
		end

		it 'should go to login page' do
			site.home_page.open_login
		end

		it 'should open the Create account page' do
			site.login_page.open_form
		end

		it 'should try to submit an empty form' do
			site.account_page.submit_form
			sleep 2
		end

		it 'should enter the first name' do
			site.account_page.enter_first_name 'John'
		end

		it 'should enter the last name' do
			site.account_page.enter_last_name 'Doe'
		end

		it 'should enter the wrong email format' do
			site.account_page.enter_email '062'
			sleep 2
		end

		it 'should enter the phone number' do
			site.account_page.enter_phone_number '062062062'
			sleep 2
		end

		it 'should choose the country' do
			site.account_page.open_countries
			site.account_page.choose_country
		end

		it 'should choose the city' do
			site.account_page.open_cities
			site.account_page.choose_city
		end

		it 'should enter password' do
			site.account_page.enter_password 'sifrazaaplikaciju'
			sleep 2
		end

		it 'should confirm the password' do
			site.account_page.confirm_password 'sifrazaaplikaciju'
			sleep 2
		end

		it 'should submit the form' do
			site.account_page.submit_form
			sleep 2
		end

		it 'should clear invalid email' do
			site.account_page.clear_email
			sleep 1
		end

		it 'should enter valid e-mail format' do
			site.account_page.enter_email 'johndoe@gmail.com' 
			sleep 2
		end 

		it 'should enter delete phone number' do
			site.account_page.clear_phone
			sleep 2
		end

		it 'should enter invalid phone number format' do
			site.account_page.enter_phone_number 'wrong'
			sleep 1
		end

		it 'should try to submit form' do
			site.account_page.submit_form
			sleep 2
		end

		it 'should delete invalid phone number' do
			site.account_page.clear_phone
			sleep 1
		end

		it 'should enter valid phone number' do
			site.account_page.enter_phone_number '062062062'
			sleep 1
		end

		it 'should clear one password' do
			site.account_page.clear_password
			sleep 1
		end

		it 'should enter different passwords' do
			site.account_page.confirm_password 'somethingelse'
		end

		it 'should try to submit the form' do
			site.account_page.submit_form
			sleep 2
		end

		it 'should clear invalid password' do
			site.account_page.clear_password
			sleep 1
		end

		it 'should enter valid password' do
			site.account_page.confirm_password 'sifrazaaplikaciju'
			sleep 1
		end

		it 'should submit the form and create account' do
			site.account_page.submit_form
			sleep 2
		end

		
	end
end