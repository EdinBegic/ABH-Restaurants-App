require 'require_all'
require_all './libs'

class ReservationHistoryPage < BaseClass

	attr_accessor :reservation_time

	def initialize(browser)
		super(browser)

		@reservation_time = browser.element(visible_text: '2019-12-26-20:30')
	end

	def reservation
		reservation_time.present?
	end
end