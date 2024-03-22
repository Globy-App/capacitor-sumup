# capacitor-sumup

Capacitor plugin to connect capacitor based applications to the native SumUp SDK to take card payments.

## Install

```bash
npm install capacitor-sumup
npx cap sync
```

## Compatibility of this plugin
The aim of this plugin is to be as close to the Native SDK's as possible. This means that the returned result code and message is chosen by the SDK and the SDK probably has the correct documentation on how to interpret the code / message.

### Android
The complete function set of this plugin is implemented on Android. This is the reference implementation.

### Web
Sumup does not provide a way to integrate the SDK on web. The SumUp Payment Switch API does exist but cannot be integrated as nicely as the native SDK's. The native SDK's allow waiting for the user to return to the application, web application don't support this.

### IOS
Not implemented yet.

## Requirements
See [SumUp Android SDK documentation](https://github.com/sumup/sumup-android-sdk) on how to setup your account to accept calls from the SDK.

## API

<docgen-index>

* [`login(...)`](#login)
* [`logout()`](#logout)
* [`makePayment(...)`](#makepayment)
* [`openCardReaderPage()`](#opencardreaderpage)
* [`prepareForCheckout()`](#prepareforcheckout)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)
* [Enums](#enums)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### login(...)

```typescript
login(options: LoginOptions) => Promise<SumupResponse>
```

Logout the user

| Param         | Type                                                  | Description                                                                                                     |
| ------------- | ----------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- |
| **`options`** | <code><a href="#loginoptions">LoginOptions</a></code> | Options to use when loggin in. Must contain an affiliate key matching the bundle identifier of the application. |

**Returns:** <code>Promise&lt;<a href="#sumupresponse">SumupResponse</a>&gt;</code>

--------------------


### logout()

```typescript
logout() => Promise<void>
```

Logout the user

--------------------


### makePayment(...)

```typescript
makePayment(options: PaymentOptions) => Promise<CheckoutResponse>
```

Trigger a payment. SumUp will open a window that helps the user letting the customer pay.

| Param         | Type                                                      | Description            |
| ------------- | --------------------------------------------------------- | ---------------------- |
| **`options`** | <code><a href="#paymentoptions">PaymentOptions</a></code> | Options of the payment |

**Returns:** <code>Promise&lt;<a href="#checkoutresponse">CheckoutResponse</a>&gt;</code>

--------------------


### openCardReaderPage()

```typescript
openCardReaderPage() => Promise<void>
```

Opens a page to configure a SumUp card reader on the device.

--------------------


### prepareForCheckout()

```typescript
prepareForCheckout() => Promise<void>
```

NOT IMPLEMENTED (yet)

--------------------


### Interfaces


#### SuccesSumupResponse

| Prop             | Type                                                            |
| ---------------- | --------------------------------------------------------------- |
| **`ResultCode`** | <code><a href="#successresultcode">SuccessResultCode</a></code> |
| **`Message`**    | <code>string</code>                                             |


#### ErrorSumupResponse

| Prop             | Type                                                        |
| ---------------- | ----------------------------------------------------------- |
| **`ResultCode`** | <code><a href="#errorresultcode">ErrorResultCode</a></code> |
| **`Message`**    | <code>string</code>                                         |


#### LoginOptions

| Prop               | Type                |
| ------------------ | ------------------- |
| **`affiliatekey`** | <code>string</code> |


#### SuccessCheckoutResponse

| Prop                  | Type                                                            |
| --------------------- | --------------------------------------------------------------- |
| **`ResultCode`**      | <code><a href="#successresultcode">SuccessResultCode</a></code> |
| **`Message`**         | <code>string</code>                                             |
| **`TransactionCode`** | <code>string</code>                                             |
| **`TransactionInfo`** | <code><a href="#transactioninfo">TransactionInfo</a></code>     |
| **`ReceiptSent`**     | <code>boolean</code>                                            |


#### TransactionInfo

| Prop                       | Type                                                    |
| -------------------------- | ------------------------------------------------------- |
| **`TransactionCode`**      | <code>string</code>                                     |
| **`MerchantCode`**         | <code>string</code>                                     |
| **`Amount`**               | <code>number</code>                                     |
| **`ForeignTransactionId`** | <code>string</code>                                     |
| **`Currency`**             | <code><a href="#currency">Currency</a></code>           |
| **`PaymentStatus`**        | <code><a href="#paymentstatus">PaymentStatus</a></code> |
| **`PaymentType`**          | <code><a href="#paymenttype">PaymentType</a></code>     |
| **`EntryMode`**            | <code>string</code>                                     |
| **`CardType`**             | <code>string</code>                                     |


#### PaymentOptions

| Prop                       | Type                                          |
| -------------------------- | --------------------------------------------- |
| **`total`**                | <code>number</code>                           |
| **`currency`**             | <code><a href="#currency">Currency</a></code> |
| **`title`**                | <code>string</code>                           |
| **`receiptEmail`**         | <code>string</code>                           |
| **`receiptSMS`**           | <code>string</code>                           |
| **`additionalInfo`**       | <code>{ [key: string]: string; }</code>       |
| **`foreignTransactionId`** | <code>string</code>                           |
| **`skipSuccessScreen`**    | <code>boolean</code>                          |
| **`skipFailedScreen`**     | <code>boolean</code>                          |


### Type Aliases


#### SumupResponse

<code><a href="#successumupresponse">SuccesSumupResponse</a> | <a href="#errorsumupresponse">ErrorSumupResponse</a></code>


#### CheckoutResponse

<code><a href="#successcheckoutresponse">SuccessCheckoutResponse</a> | <a href="#errorsumupresponse">ErrorSumupResponse</a></code>


### Enums


#### SuccessResultCode

| Members          | Value          |
| ---------------- | -------------- |
| **`SUCCESSFUL`** | <code>1</code> |


#### ErrorResultCode

| Members                             | Value           |
| ----------------------------------- | --------------- |
| **`ERROR_TRANSACTION_FAILED`**      | <code>2</code>  |
| **`ERROR_GEOLOCATION_REQUIRED`**    | <code>3</code>  |
| **`ERROR_INVALID_PARAM`**           | <code>4</code>  |
| **`ERROR_INVALID_TOKEN`**           | <code>5</code>  |
| **`ERROR_NO_CONNECTIVITY`**         | <code>6</code>  |
| **`ERROR_PERMISSION_DENIED`**       | <code>7</code>  |
| **`ERROR_NOT_LOGGED_IN`**           | <code>8</code>  |
| **`ERROR_DUPLICATE_FOREIGN_TX_ID`** | <code>9</code>  |
| **`ERROR_INVALID_AFFILIATE_KEY`**   | <code>10</code> |
| **`ERROR_ALREADY_LOGGED_IN`**       | <code>11</code> |
| **`ERROR_INVALID_AMOUNT_DECIMALS`** | <code>12</code> |
| **`ERROR_API_LEVEL_TOO_LOW`**       | <code>13</code> |


#### Currency

| Members   | Value              |
| --------- | ------------------ |
| **`BGN`** | <code>"BGN"</code> |
| **`BRL`** | <code>"BRL"</code> |
| **`CHF`** | <code>"CHF"</code> |
| **`CLP`** | <code>"CLP"</code> |
| **`COP`** | <code>"COP"</code> |
| **`CZK`** | <code>"CZK"</code> |
| **`DKK`** | <code>"DKK"</code> |
| **`EUR`** | <code>"EUR"</code> |
| **`GBP`** | <code>"GBP"</code> |
| **`HRK`** | <code>"HRK"</code> |
| **`HUF`** | <code>"HUF"</code> |
| **`NOK`** | <code>"NOK"</code> |
| **`PEN`** | <code>"PEN"</code> |
| **`PLN`** | <code>"PLN"</code> |
| **`RON`** | <code>"RON"</code> |
| **`SEK`** | <code>"SEK"</code> |
| **`USD`** | <code>"USD"</code> |


#### PaymentStatus

| Members          | Value                     |
| ---------------- | ------------------------- |
| **`PENDING`**    | <code>"PENDING"</code>    |
| **`SUCCESSFUL`** | <code>"SUCCESSFUL"</code> |
| **`CANCELLED`**  | <code>"CANCELLED"</code>  |
| **`FAILED`**     | <code>"FAILED"</code>     |


#### PaymentType

| Members         | Value                    |
| --------------- | ------------------------ |
| **`CASH`**      | <code>"CASH"</code>      |
| **`POS`**       | <code>"POS"</code>       |
| **`ECOM`**      | <code>"ECOM"</code>      |
| **`UNKNOWN`**   | <code>"UNKNOWN"</code>   |
| **`RECURRING`** | <code>"RECURRING"</code> |
| **`BITCOIN`**   | <code>"BITCOIN"</code>   |
| **`BALANCE`**   | <code>"BALANCE"</code>   |

</docgen-api>
