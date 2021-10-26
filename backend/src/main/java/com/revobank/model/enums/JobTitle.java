package com.revobank.model.enums;

public enum JobTitle {

	COUNTER {
		@Override
		public Double getInicialBalance() {			
			return 5000.0;
		}

		@Override
		public boolean shouldBlockByInvalidAmount() {
			return true;
		}
	}, PROGRAMMER {
		@Override
		public Double getInicialBalance() {			
			return 8000.0;
		}

		@Override
		public boolean shouldBlockByInvalidAmount() {
			return true;
		}
	}, DIRECTOR {
		@Override
		public Double getInicialBalance() {			
			return 20000.0;
		}

		@Override
		public boolean shouldBlockByInvalidAmount() {			
			return false;
		}
	};
	
	public abstract Double getInicialBalance();

	public abstract boolean shouldBlockByInvalidAmount();
}
