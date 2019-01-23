require 'rspec'
require 'watir'
require 'selenium-webdriver'

browser = Watir::Browser.new :chrome

RSpec.configure do |config|
  config.color = true
  config.formatter = :documentation
  config.before(:each) { @browser = browser }
  config.before(:all) {browser.window.maximize}
  config.after(:suite) { browser.close unless browser.nil? }
end


describe 'ABH restaurants regression test' do

	context 'When rating the place' do

		it 'should open ABH restaurants page' do
			@browser.goto('https://abh-restaurants.herokuapp.com')
		end

		it 'should go to login page' do
			@browser.a(id: 'login-link').click
		end

		it 'should enter the email' do
			@browser.input(:placeholder => 'Email').send_keys 'janedoe@gmail.com'
		end

		it 'should enter the password' do
			@browser.input(:placeholder => 'Password').send_keys 'sifrazaaplikaciju'
		end

		it 'should login to application' do
			@browser.button(class: ["form-control", "login-button", "btn"]).click
		end

		it 'should close the confirmation window' do
			@browser.button(class: ["swal2-confirm", "swal2-styled"]).click
		end

		it 'should go to the Restaurants section' do
			@browser.link(href: "/restaurant-list").wait_until_present
			@browser.link(href: "/restaurant-list").click!
		end


		it 'should open the restaurants page' do
			@browser.button(class: ["form-control", "reserve-button--list", "btn"]).click
		end

		it 'should open the rating form' do
			@browser.button(class: ["form-control", "rate-button", "btn"]).click
		end

		it 'should write a review' do
			@browser.textarea(placeholder: 'Write a review').send_keys "Good, but not great."
			sleep 2
		end

		it 'should rate the place' do
			@browser.element(fill: 'url(#star-rating-star-2)').click
			sleep 2
		end

		it 'should send the review' do
			@browser.button(class: ["form-control", "modal-button", "btn"]).click
			sleep 2
		end

		it 'should close the confirmation window' do
			@browser.button(class: ["swal2-confirm", "swal2-styled"]).click
		end

	end
end