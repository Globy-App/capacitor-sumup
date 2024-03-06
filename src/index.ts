import { registerPlugin } from '@capacitor/core';

import type { SumupPluginPlugin } from './definitions';

const SumupPlugin = registerPlugin<SumupPluginPlugin>('SumupPlugin', {
  web: () => import('./web').then(m => new m.SumupPluginWeb()),
});

export * from './definitions';
export { SumupPlugin };
