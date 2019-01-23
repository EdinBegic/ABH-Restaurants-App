require 'require_all'
require_all './libs'

class RestaurantPage < BaseClass

	attr_accessor :find_table_button
	attr_accessor :date_input
	attr_accessor :time_element
	attr_accessor :time_input
	attr_accessor :another_time
	attr_accessor :rating_form
	attr_accessor :review_area
	attr_accessor :rate_star
	attr_accessor :review_button
	attr_accessor :profile_button
	attr_accessor :reservation_history
	attr_accessor :table_dropdown
	attr_accessor :table_option


	def initialize(browser)
		super(browser)

		@find_table_button = @browser.button(class: ["form-control", "find-table-button", "btn"])
		@date_input = @browser.input(class: ["calendar-picker", "ember-view"])
		@time_element = @browser.element(class: "ember-power-select-selected-item")
		@time_input = @browser.input(class: "ember-power-select-search-input")
		@another_time = @browser.button(class: ["form-control", "suggested-reservation-button btn"])
		@rating_form = @browser.button(class: ["form-control", "rate-button", "btn"])
		@review_area = @browser.textarea(placeholder: 'Write a review')
		@rate_star = @browser.element(fill: 'url(#star-rating-star-2)')
		@review_button = @browser.button(class: ["form-control", "modal-button", "btn"])
		@profile_button = @browser.element(class: ["history-button", "dropdown-toggle", "ember-view"])
		@reservation_history = @browser.a(class: ["reservation-button", "dropdown-item", "ember-view"])
		@table_dropdown = @browser.element(class: ["form-control", "select-table"])
		@table_option = @browser.option(value: "4")

	end

	def open_tables
		table_dropdown.click
	end

	def choose_table
		table_option.click
	end

	def choose_date(date)
		date_input.send_keys date
	end

	def clear_date
		date_input.to_subtype.clear
	end

	def open_time
		time_element.click
	end

	def choose_time(time)
		time_input.send_keys time
	end

	def send_time
		time_input.send_keys :enter
	end

	def clear_time
		time_input.send_keys :backspace
		time_input.send_keys :backspace
		time_input.send_keys :backspace
		time_input.send_keys :backspace
		time_input.send_keys :backspace

	end

	def find_table
		find_table_button.click
	end

	def choose_another_time
		another_time.click
	end

	def open_rating_form
		rating_form.click
	end

	def write_review(review)
		review_area.send_keys review
	end

	def rate_place
		rate_star.click
	end

	def send_review
		review_button.click
	end

	def open_profile
		profile_button.click
	end

	def open_reservation_history
		reservation_history.click
	end

end