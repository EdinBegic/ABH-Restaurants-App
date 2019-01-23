require 'require_all'
require_all './libs'

class LoginPage < BaseClass
	attr_accessor :create_account
	attr_accessor :email_field
	attr_accessor :password_field
	attr_accessor :login_button

	def initialize(browser)
		super(browser)

		@create_account = @browser.a(id: 'registration-link')
		@email_field = browser.input(:placeholder => 'Email')
		@password_field = @browser.input(:placeholder => 'Password')
		@login_button = @browser.button(class: ["form-control", "login-button", "btn"])

	end

	def open_form
		create_account.click
	end

	def enter_email(email)
		email_field.send_keys email
	end

	def enter_password(password)
		password_field.send_keys password
	end

	def login
		login_button.click
	end
	
end