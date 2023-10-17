# compass

Compass

## Install

```bash
npm install compass
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`getHeading()`](#getheading)
* [`addListener('compassUpdate', ...)`](#addlistenercompassupdate)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### getHeading()

```typescript
getHeading() => Promise<{ heading: number; } | undefined>
```

**Returns:** <code>Promise&lt;{ heading: number; }&gt;</code>

--------------------


### addListener('compassUpdate', ...)

```typescript
addListener(eventName: 'compassUpdate', listenerFunc: (info: compassUpdate) => void) => Promise<PluginListenerHandle> & PluginListenerHandle
```

| Param              | Type                                                                       |
| ------------------ | -------------------------------------------------------------------------- |
| **`eventName`**    | <code>'<a href="#compassupdate">compassUpdate</a>'</code>                  |
| **`listenerFunc`** | <code>(info: <a href="#compassupdate">compassUpdate</a>) =&gt; void</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### Interfaces


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |


#### compassUpdate

| Prop          | Type                |
| ------------- | ------------------- |
| **`heading`** | <code>number</code> |

</docgen-api>
