//
//  ViewData.swift
//  iosApp
//
//  Created by myungpyo on 2021/01/10.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

enum ViewData<T> {
    case empty
    case inProgress
    case success(T)
    case fail(Error)
}
