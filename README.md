# capacitor-sumup

Capacitor plugin to connect capacitor based applications to the native SumUp SDK to take card payments.

## Install

```bash
npm install capacitor-sumup
npx cap sync
```

## API

<docgen-index>

* [`login(...)`](#login)
* [`makePayment(...)`](#makepayment)
* [`prepareForCheckout()`](#prepareforcheckout)
* [`openCardReaderPage()`](#opencardreaderpage)
* [Interfaces](#interfaces)
* [Enums](#enums)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### login(...)

```typescript
login(options: LoginOptions) => Promise<LoginResponse>
```

| Param         | Type                                                  |
| ------------- | ----------------------------------------------------- |
| **`options`** | <code><a href="#loginoptions">LoginOptions</a></code> |

**Returns:** <code>Promise&lt;<a href="#loginresponse">LoginResponse</a>&gt;</code>

--------------------


### makePayment(...)

```typescript
makePayment(options: PaymentOptions) => Promise<CheckoutResponse>
```

| Param         | Type                                                      |
| ------------- | --------------------------------------------------------- |
| **`options`** | <code><a href="#paymentoptions">PaymentOptions</a></code> |

**Returns:** <code>Promise&lt;<a href="#checkoutresponse">CheckoutResponse</a>&gt;</code>

--------------------


### prepareForCheckout()

```typescript
prepareForCheckout() => Promise<void>
```

--------------------


### openCardReaderPage()

```typescript
openCardReaderPage() => Promise<void>
```

--------------------


### Interfaces


#### LoginResponse

| Prop               | Type                |
| ------------------ | ------------------- |
| **`affiliateKey`** | <code>string</code> |


#### LoginOptions

| Prop               | Type                |
| ------------------ | ------------------- |
| **`affiliatekey`** | <code>string</code> |
| **`accessToken`**  | <code>string</code> |


#### CheckoutResponse

| Prop                   | Type                                                        |
| ---------------------- | ----------------------------------------------------------- |
| **`ResultCode`**       | <code><a href="#resultcode">ResultCode</a></code>           |
| **`Message`**          | <code>string</code>                                         |
| **`TransactionCode`**  | <code>string</code>                                         |
| **`TransactionInfor`** | <code><a href="#transactioninfo">TransactionInfo</a></code> |
| **`ReceiptSent`**      | <code>boolean</code>                                        |


#### TransactionInfo

| Prop                  | Type                                                    |
| --------------------- | ------------------------------------------------------- |
| **`TransactionCode`** | <code>string</code>                                     |
| **`MerchantCode`**    | <code>string</code>                                     |
| **`Amount`**          | <code>number</code>                                     |
| **`Tip`**             | <code>number</code>                                     |
| **`VAT`**             | <code>number</code>                                     |
| **`Currency`**        | <code><a href="#currency">Currency</a></code>           |
| **`PaymentStatus`**   | <code><a href="#paymentstatus">PaymentStatus</a></code> |
| **`EntryMode`**       | <code>string</code>                                     |
| **`Installments`**    | <code>number</code>                                     |
| **`CardType`**        | <code>string</code>                                     |


#### PaymentOptions

| Prop                       | Type                                          |
| -------------------------- | --------------------------------------------- |
| **`total`**                | <code>number</code>                           |
| **`currency`**             | <code><a href="#currency">Currency</a></code> |
| **`enableTipping`**        | <code>boolean</code>                          |
| **`tip`**                  | <code>number</code>                           |
| **`title`**                | <code>string</code>                           |
| **`receiptEmail`**         | <code>string</code>                           |
| **`receiptSMS`**           | <code>string</code>                           |
| **`additionalInfo`**       | <code>{ [key: string]: string; }</code>       |
| **`foreignTransactionId`** | <code>string</code>                           |
| **`skipSuccessScreen`**    | <code>boolean</code>                          |
| **`skipFailedScreen`**     | <code>boolean</code>                          |


### Enums


#### ResultCode

| Members                             | Value           |
| ----------------------------------- | --------------- |
| **`SUCCESSFUL`**                    | <code>1</code>  |
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
| **`EUR`** | <code>"EUR"</code> |


#### PaymentStatus

| Members          | Value                     |
| ---------------- | ------------------------- |
| **`PENDING`**    | <code>"PENDING"</code>    |
| **`SUCCESSFUL`** | <code>"SUCCESSFUL"</code> |
| **`CANCELLED`**  | <code>"CANCELLED"</code>  |
| **`FAILED`**     | <code>"FAILED"</code>     |

</docgen-api>
