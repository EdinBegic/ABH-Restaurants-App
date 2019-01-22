require 'require_all'
require_all './libs'

class ConfirmationPage < BaseClass

	attr_accessor :complete_reservation_button

	def intitialize(browser)
		super(browser)

		@complete_reservation_button = @browser.element(class: ["form-control", "login-button--reservation", "btn"])

	end

	def complete_reservation
		complete_reservation_button.click
	end
end
