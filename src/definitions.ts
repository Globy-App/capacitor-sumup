export interface LoginOptions {
  affiliatekey: string,
  accessToken: string,
}

export interface LoginResponse {
  affiliateKey: string
}

export interface PaymentOptions {
  total: number,
  currency: Currency,
  enableTipping?: boolean,
  tip?: number,
  title?: string,
  receiptEmail?: string,
  receiptSMS?: string,
  additionalInfo?: { [key: string]: string },
  foreignTransactionId?: string, // TODO: Automagically make this a UUID if its not set?
  skipSuccessScreen?: boolean,
  skipFailedScreen?: boolean,
}

export enum Currency {
  EUR = "EUR"
}

export enum ResultCode {
  SUCCESSFUL =  1,
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

export enum PaymentStatus {
  PENDING = "PENDING",
  SUCCESSFUL = "SUCCESSFUL",
  CANCELLED = "CANCELLED",
  FAILED = "FAILED",
}

export interface TransactionInfo {
  TransactionCode: string,
  MerchantCode: string,
  Amount: number,
  Tip: number,
  VAT: number,
  Currency: Currency,
  PaymentStatus: PaymentStatus,
  EntryMode: string,
  Installments: number,
  CardType: string,
}

export interface CheckoutResponse {
  ResultCode: ResultCode
  Message: string,
  TransactionCode: string,
  TransactionInfor: TransactionInfo,
  ReceiptSent: boolean
}

export interface SumupPlugin {
  login(options: LoginOptions): Promise<LoginResponse>;
  logout(): Promise<void>;
  makePayment(options: PaymentOptions): Promise<CheckoutResponse>;
  prepareForCheckout(): Promise<void>;
  openCardReaderPage(): Promise<void>;
}
