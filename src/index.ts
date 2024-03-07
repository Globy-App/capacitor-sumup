import { registerPlugin } from '@capacitor/core';

import type { SumupPlugin } from './definitions';

const SumUp = registerPlugin<SumupPlugin>('SumUp', {
  web: () => import('./web').then(m => new m.SumupWeb()),
});

export * from './definitions';
export { SumUp };