//
//  LazyInject.swift
//  iosApp
//
//  Created by myungpyo on 2021/01/07.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import UIKit

@propertyWrapper
struct LazyInject<T> {
    private lazy var value: T = {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        return appDelegate.container.resolve(T.self)!
    }()
    
    var wrappedValue: T {
        mutating get { return value }
    }
    
}
