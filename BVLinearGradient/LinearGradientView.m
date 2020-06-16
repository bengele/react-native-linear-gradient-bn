//
//  LinearGradientView.m
//  BVLinearGradient
//
//  Created by 夏友彬 on 2020/6/11.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LinearGradientView.h"
#import "BVLinearGradient.h"


@interface LinearGradientView()

@end

@implementation LinearGradientView{
    BVLinearGradient* linerView;
    UIButton* button;
}


- (instancetype)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        [self initView:frame];
    }
    return self;
}

-(void)initView:(CGRect)frame{
    self->linerView = [[BVLinearGradient alloc] initWithFrame:CGRectMake(0, 0, frame.size.width, frame.size.height)];
    self->button = [UIButton buttonWithType:UIButtonTypeCustom];
    self->button.frame = CGRectMake(0, 0, frame.size.width,frame.size.height );

    self.useAngle = true;
    self->linerView.useAngle = true;
    self->linerView.angle = 0;
    
    [self->linerView addSubview:self->button];
    [self addSubview:self->linerView];
}

- (NSArray *)gradientColors{
    return self->linerView.colors;
}

- (void)setGradientColors:(NSArray<UIColor *> *)gradientColors{
    self->linerView.colors = gradientColors;
}

- (NSArray *)locations{
    return self.locations;
}

- (void)setLocations:(NSArray *)locations{
    self->linerView.locations = locations;
}

- (BOOL)useAngle
{
    return self->linerView.useAngle;
}
    
- (void)setUseAngle:(BOOL)useAngle
{
    self->linerView.useAngle = useAngle;
}

- (CGPoint)angleCenter
{
    return self->linerView.angleCenter;
}
    
- (void)setAngleCenter:(CGPoint)angleCenter
{
    self->linerView.angleCenter = angleCenter;
}

- (CGFloat)angle
{
    return self->linerView.angle;
}
    
- (void)setAngle:(CGFloat)angle
{
    self->linerView.angle = angle;
}

- (CGFloat)borderRadius{
    return self->linerView.borderRadius;
}

- (void)setBorderRadius:(CGFloat)borderRadius{
    self->linerView.borderRadius = borderRadius;
}

-(void)setText:(NSString*)text{
    [self->button setTitle:text forState:UIControlStateNormal];
}

-(NSString*)text{
    return self->button.titleLabel.text;
}

-(void)addTouchTarget:(nullable id)cls :(nullable SEL)action{
    if (self->button == nil) {
        return;
    }
    [self->button addTarget:cls action:action forControlEvents:UIControlEventTouchUpInside];
}

@end
