export interface compassPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
