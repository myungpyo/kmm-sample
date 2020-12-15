//
//  TableViewAdapterDelegate.swift
//  iosApp
//
//  Created by myungpyo on 2021/01/09.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import UIKit

protocol TableViewAdapterDelegate: UITableViewDataSource, UITableViewDelegate {
    associatedtype Item
    
    func bind(items: [Item])
}
