# react-native-linear-gradient-bn

原作者：https://github.com/react-native-community/react-native-linear-gradient

##### Android   

``` java    
    LinearGradientView view = new LinearGradientView(mContext);
    int width = dip2px(mContext,200);
    int height = dip2px(mContext,48);
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
    view.setLayoutParams(params);
    view.setUseAngle(true);
    view.setAngle(0);
    float radius = dip2px(mContext,24);

    view.setBorderRadius(radius);

//        view.setStartPosition(new float[]{0f, 1f});
//        view.setEndPosition(new float[]{0f, 0f});
//        view.setBorderRadii(new float[]{radius,radius,radius,radius,radius,radius,radius,radius});

    int[] colors = new int[]{
            Color.parseColor("#FFA50A"),
            Color.parseColor("#FFB604"),
            Color.parseColor("#F3B913"),
    };
    view.setColors(colors);
    float[] pos = new float[]{
            1.f / 5,
            2.f / 5,
            1
    };
    view.setLocations(pos);
    view.setText("上一步");
    view.setTextSize(18);
    gradientLayout.addView(view);
```

##### ios   

``` swift
    let view = LinearGradientView.init(frame: CGRect(x: 0, y: 100, width: 300, height: 48))
    view.gradientColors = [UIColor(hexString: "#FFA50A"),UIColor(hexString: "#FFB604"),UIColor(hexString: "#F3B913")]
    view.locations = [0.2,0.4,1]
    view.borderRadius = 24
    view.text = "下一步"
    view.addTouchTarget(self, #selector(doOpenRN))    
    self.view.addSubview(view)

```

##### rn
```js
    <LinearGradient colors={['#FFA50A', '#FFB604', '#F3B913']} 
        style={{ 
            marginTop:100,
            width:200,
            height: 48,
            paddingLeft: 15,
            paddingRight: 15,
            borderRadius: 24}} 
        >
        <Text style={styles.buttonText}>
            {"下一步"}
        </Text>
    </LinearGradient>

```
