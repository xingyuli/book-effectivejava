package org.swordess.effectivejava.chapter6.use_enum_instead_of_int.payrollday.impl2;

// The strategy enum pattern
public enum PayrollDay {

	MONDAY(PayType.WEEKDAY),
	TUESDAY(PayType.WEEKDAY),
	WEDNESDAY(PayType.WEEKDAY),
	THURSDAY(PayType.WEEKDAY),
	FRIDAY(PayType.WEEKDAY),
	SATURDAY(PayType.WEEKEND),
	SUNDAY(PayType.WEEKEND);

	private final PayType payType;
	
	private PayrollDay(PayType payType) {
		this.payType = payType;
	}
	
	double pay(double hoursWorked, double payRate) {
		return payType.pay(hoursWorked, payRate);
	}
	
	// The strategy enum type
	private enum PayType {
		
		WEEKDAY {
			double overtimePay(double hrs, double payRate) {
				return hrs * payRate / 2;
			}
		},
		WEEKEND {
			double overtimePay(double hrs, double payRate) {
				return hrs <= HOURS_PER_SHIFT ? 0 :
					(hrs - HOURS_PER_SHIFT) * payRate / 2;
			}
		};
	
		private static final int HOURS_PER_SHIFT = 8;
		
		abstract double overtimePay(double hrs, double payRate);
		
		double pay(double hoursWorked, double payRate) {
			double basePay = hoursWorked * payRate;
			return basePay + overtimePay(hoursWorked, payRate);
		}
		
	}

}
