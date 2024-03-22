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
  ForeignTransactionId: string,
  Currency: Currency,
  PaymentStatus: PaymentStatus,
  PaymentType: PaymentType,
  EntryMode: string, 
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
  /**
   * Logout the user
   * 
   * @param options Options to use when loggin in. Must contain an affiliate key matching the bundle identifier of the application.
   */
  login(options: LoginOptions): Promise<SumupResponse>;
  /**
   * Logout the user
   */
  logout(): Promise<void>;
  /**
   * Trigger a payment. SumUp will open a window that helps the user letting the customer pay.
   * 
   * @param options Options of the payment
   */
  makePayment(options: PaymentOptions): Promise<CheckoutResponse>;
  /**
   * Opens a page to configure a SumUp card reader on the device.
   * May never resolve, this is due to SumUp's implementation.
   */
  openCardReaderPage(): Promise<SumupResponse>;
  /**
   * Offers the possibility to connect the card reader ahead of initiating the checkout which speeds up the overall checkout time.
   */
  prepareForCheckout(): Promise<void>;
}
