require 'require_all'
require_all './libs'

class AccountPage < BaseClass
	attr_accessor :create_account
	attr_accessor :first_name_field
	attr_accessor :last_name_field
	attr_accessor :email_field
	attr_accessor :phone_number_field
	attr_accessor :country_button
	attr_accessor :country_option
	attr_accessor :city_button
	attr_accessor :city_option
	attr_accessor :password_field
	attr_accessor :confirm_password_field

	def initialize(browser)
		super(browser)

		@create_account = @browser.button(visible_text: 'Create Account')
		@first_name_field = @browser.input(placeholder: 'First Name')
		@last_name_field = @browser.input(placeholder: 'Last Name')
		@email_field = @browser.input(placeholder: 'Email')
		@phone_number_field = @browser.input(placeholder: 'Phone Number')
		@country_button = @browser.element(id: "select-right")
		@country_option = @browser.option(value: 'Bosnia and Herzegovina')
		@city_button = @browser.element(id: "select-left")
		@city_option = @browser.option(value: '1')
		@password_field = @browser.input(placeholder: 'Password')
		@confirm_password_field = @browser.input(placeholder: 'Confirm password')
	end

	def submit_form
		create_account.click
	end

	def enter_first_name(first_name)
		first_name_field.send_keys first_name
	end

	def enter_last_name(last_name)
		last_name_field.send_keys last_name
	end

	def enter_email(email)
		email_field.send_keys email
	end

	def enter_phone_number(phone_number)
		phone_number_field.send_keys phone_number
	end

	def open_countries
		country_button.click
	end

	def choose_country
		country_option.click
	end

	def open_cities
		city_button.click

	def choose_city
		city_option.click
	end

	def enter_password(password)
		password_field.send_keys password
	end

	def confirm_password(password_confirmation)
		confirm_password_field.send_keys password_confirmation
	end

	def clear_email
		email_field.to_subtype.clear
	end

	def clear_phone
		phone_number_field.to_subtype.clear
	end

	def clear_password
		confirm_password_field.to_subtype.clear
	end
end
end