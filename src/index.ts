import { registerPlugin } from '@capacitor/core';

import type { SumupPlugin } from './definitions';

const SumupPlugin = registerPlugin<SumupPlugin>('SumupPlugin', {
  web: () => import('./web').then(m => new m.SumupWeb()),
});

export * from './definitions';
export { SumupPlugin };
