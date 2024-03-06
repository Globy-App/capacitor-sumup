import { WebPlugin } from '@capacitor/core';

import type { CheckoutResponse, LoginOptions, LoginResponse, PaymentOptions, SumupPlugin } from './definitions';

export class SumupWeb extends WebPlugin implements SumupPlugin {
  login(options: LoginOptions): Promise<LoginResponse> {
    console.log(options)
    throw this.unimplemented('Not implemented on web.');
  }
  makePayment(options: PaymentOptions): Promise<CheckoutResponse> {
    console.log(options)
    throw this.unimplemented('Not implemented on web.');
  }
  prepareForCheckout(): Promise<void> {
    throw this.unimplemented('Not implemented on web.');
  }
  openCardReaderPage(): Promise<void> {
    throw this.unimplemented('Not implemented on web.');
  }
}
