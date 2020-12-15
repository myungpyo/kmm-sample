//
//  UITableViewExtensions.swift
//  iosApp
//
//  Created by myungpyo on 2021/01/09.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import UIKit

extension UITableView {
    func installAdapter<T: TableViewAdapterDelegate>(adapter: T?) {
        self.delegate = adapter
        self.dataSource = adapter
    }
}
