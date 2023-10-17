import type { PluginListenerHandle } from "@capacitor/core";

export interface compassPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  getHeading(): Promise<{heading: number} | undefined>;

  addListener(
  eventName: 'compassUpdate',
  listenerFunc: (info: compassUpdate) => void,
  ): Promise<PluginListenerHandle> & PluginListenerHandle;
}

export interface compassUpdate{
  heading: number;
}