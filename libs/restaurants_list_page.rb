require 'require_all'
require_all './libs'

class RestaurantsListPage < BaseClass

	attr_accessor :restaurant_name
	attr_accessor :search_button
	attr_accessor :restaurant_button
	attr_accessor :sort_button
	attr_accessor :asc_button
	attr_accessor :filter_button
	attr_accessor :price_filter


	def initialize(browser)
		super(browser)

		@restaurant_name = @browser.input(placeholder: 'Search for a restaurant...')
		@search_button = @browser.button(class: ["form-control", "table-button", "btn"])
		@restaurant_button = @browser.button(class: ["form-control", "reserve-button--list", "btn"])
		@sort_button = @browser.button(text: 'Sort by')
		@asc_button = @browser.element(class: ["custom-control-label", "order-label"])
		@filter_button = @browser.button(text: 'Filter by')
		@price_filter = @browser.element(d: 'm10.317475,12.256545c-1.05118,-0.63288 -2.168751,-1.115074
                                        -3.275256,-1.61535c-0.641773,-0.289316 -1.255884,-0.626852
                                        -1.798071,-1.096992c-1.067778,-0.928224 -0.863074,-2.43508
                                        0.387277,-3.031795c0.354082,-0.168768 0.724761,-0.223015
                                        1.100973,-0.247124c1.449522,-0.084384 2.827121,0.204932
                                        4.13833,0.892059c0.652838,0.343563 0.868607,0.23507
                                        1.089908,-0.506304c0.232366,-0.783565 0.426005,-1.579186
                                        0.641773,-2.368779c0.143846,-0.530414 -0.033195,-0.880004
                                        -0.492395,-1.103019c-0.840944,-0.403838 -1.704018,-0.693154
                                        -2.611353,-0.849867c-1.183961,-0.198905 -1.183961,-0.204932
                                        -1.189493,-1.500829c-0.005533,-1.82631 -0.005533,-1.82631
                                        -1.687421,-1.82631c-0.243431,0 -0.486862,-0.006027
                                        -0.730294,0c-0.785619,0.02411 -0.9184,0.174795
                                        -0.94053,1.036717c-0.011065,0.385755 0,0.771511
                                        -0.005533,1.163293c-0.005533,1.145211 -0.011065,1.127129
                                        -1.017985,1.524939c-2.434312,0.964388 -3.939159,2.772616
                                        -4.099603,5.665781c-0.143846,2.561656 1.084375,4.291528
                                        3.015227,5.55126c1.189493,0.777538 2.506235,1.235622
                                        3.767651,1.844392c0.492395,0.23507 0.96266,0.506304
                                        1.372067,0.880004c1.211623,1.090964 0.990322,2.90522
                                        -0.448135,3.592346c-0.769021,0.367673 -1.582303,0.458084
                                        -2.417714,0.343563c-1.289079,-0.174795 -2.522832,-0.542468
                                        -3.684663,-1.199458c-0.680501,-0.385755 -0.879672,-0.283289
                                        -1.112038,0.518359c-0.199171,0.693154 -0.376212,1.392335
                                        -0.553253,2.091517c-0.237899,0.940279 -0.149378,1.163293
                                        0.674968,1.603295c1.05118,0.554523 2.174283,0.837812
                                        3.319516,1.036717c0.896269,0.156713 0.923932,0.198905
                                        0.934997,1.211513c0.005533,0.458084 0.005533,0.922196
                                        0.011065,1.380281c0.005533,0.578633 0.260029,0.916169
                                        0.807749,0.928224c0.619643,0.012055 1.244819,0.012055
                                        1.864462,-0.006027c0.508993,-0.012055 0.769021,-0.313426
                                        0.769021,-0.873977c0,-0.626852 0.027663,-1.259732
                                        0.005533,-1.886584c-0.027663,-0.638907 0.226834,-0.964388
                                        0.791151,-1.133156c1.300144,-0.385755 2.406649,-1.145211
                                        3.258659,-2.278367c2.367922,-3.134262 1.46612,-7.721133
                                        -1.886592,-9.740321z')
	end

	def enter_restaurant_name(restaurant)
		restaurant_name.send_keys restaurant
	end

	def search_restaurant
		search_button.click
	end

	def open_restaurant
		restaurant_button.click
	end

	def sort
		sort_button.click
	end

	def sort_asc
		asc_button.click
	end

	def filter
		filter_button.click
	end

	def filter_by_price
		price_filter.click
	end

end