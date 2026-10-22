package com.ledger.enums;

public enum EntryType {
	CREDIT, //merchant gives the Goods
	PAYMENT,//merchant receives the Payment
	REVERSAL//merchant reverses the wrong entry (CREDIT OR DEBIT)
}
