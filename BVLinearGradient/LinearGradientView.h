//
//  LinearGradientView.h
//  Pods
//
//  Created by 夏友彬 on 2020/6/11.
//

#ifndef LinearGradientView_h
#define LinearGradientView_h

#import <UIKit/UIKit.h>

@interface LinearGradientView : UIView

@property (nullable, nonatomic, copy) NSArray<UIColor *> *gradientColors;
@property (nullable, nonatomic, copy) NSArray<NSNumber *> *locations;
@property (nonatomic) CGPoint startPoint;
@property (nonatomic) CGPoint endPoint;
@property (nonatomic) BOOL useAngle;
@property (nonatomic) CGPoint angleCenter;
@property (nonatomic) CGFloat angle;
@property (nonatomic) CGFloat borderRadius;
@property (nonatomic) NSString* text;


-(void)addTouchTarget:(nullable id)cls :(nullable SEL)action;

@end

#endif /* LinearGradientView_h */
