export interface LoginOptions {
  affiliatekey: string,
}

export interface SuccesSumupResponse {
  ResultCode: SuccessResultCode,
  Message: string
}

export interface ErrorSumupResponse {
  ResultCode: ErrorResultCode,
  Message: string
}

export type SumupResponse = SuccesSumupResponse | ErrorSumupResponse

export interface PaymentOptions {
  total: number,
  currency: Currency,
  title?: string,
  receiptEmail?: string,
  receiptSMS?: string,
  additionalInfo?: { [key: string]: string },
  foreignTransactionId?: string, // TODO: Automagically make this a UUID if its not set?
  skipSuccessScreen?: boolean,
  skipFailedScreen?: boolean,
}

// TODO: Expand this to all avaialble currencies
export enum Currency {
  BGN = "BGN",
  BRL = "BRL",
  CHF = "CHF",
  CLP = "CLP",
  COP = "COP",
  CZK = "CZK",
  DKK = "DKK",
  EUR = "EUR",
  GBP = "GBP",
  HRK = "HRK",
  HUF = "HUF",
  NOK = "NOK",
  PEN = "PEN",
  PLN = "PLN",
  RON = "RON",
  SEK = "SEK",
  USD = "USD",
}

export enum ErrorResultCode {
  ERROR_TRANSACTION_FAILED = 2,
  ERROR_GEOLOCATION_REQUIRED = 3,
  ERROR_INVALID_PARAM = 4,
  ERROR_INVALID_TOKEN = 5,
  ERROR_NO_CONNECTIVITY = 6,
  ERROR_PERMISSION_DENIED = 7,
  ERROR_NOT_LOGGED_IN = 8,
  ERROR_DUPLICATE_FOREIGN_TX_ID = 9,
  ERROR_INVALID_AFFILIATE_KEY = 10,
  ERROR_ALREADY_LOGGED_IN = 11,
  ERROR_INVALID_AMOUNT_DECIMALS = 12,
  ERROR_API_LEVEL_TOO_LOW = 13,
}

export enum SuccessResultCode {
  SUCCESSFUL =  1,
}

export type ResultCode = SuccessResultCode | ErrorResultCode; 

export enum PaymentStatus {
  PENDING = "PENDING",
  SUCCESSFUL = "SUCCESSFUL",
  CANCELLED = "CANCELLED",
  FAILED = "FAILED",
}

export enum PaymentType {
  CASH = "CASH",
  POS = "POS",
  ECOM = "ECOM",
  UNKNOWN = "UNKNOWN",
  RECURRING = "RECURRING",
  BITCOIN = "BITCOIN",
  BALANCE = "BALANCE"
}

export interface TransactionInfo {
  TransactionCode: string,
  MerchantCode: string,
  Amount: number,
  Tip: number,
  VAT: number,
  ForeignTransactionId: string,
  Currency: Currency,
  PaymentStatus: PaymentStatus,
  PaymentType: PaymentType,
  EntryMode: string, 
  Installments: number,
  CardType: string,
}

export interface SuccessCheckoutResponse {
  ResultCode: SuccessResultCode
  Message: string,
  TransactionCode: string,
  TransactionInfo: TransactionInfo,
  ReceiptSent: boolean
}

export type CheckoutResponse = SuccessCheckoutResponse | ErrorSumupResponse;

export interface SumupPlugin {
  login(options: LoginOptions): Promise<SumupResponse>;
  logout(): Promise<void>;
  makePayment(options: PaymentOptions): Promise<CheckoutResponse>;
  prepareForCheckout(): Promise<void>;
  openCardReaderPage(): Promise<SumupResponse>;
}
