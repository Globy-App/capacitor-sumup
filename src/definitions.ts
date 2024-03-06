export interface SumupPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
