//
//  Mapper.swift
//  iosApp
//
//  Created by myungpyo on 2021/01/10.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

protocol Mapper {
    associatedtype From
    associatedtype To
    func map(data: From) -> To
    func mapAll(data: [From]) -> [To]
    func reverse(data: To) -> From
    func reverseAll(data: [To]) -> [From]
}

extension Mapper {
    func mapAll(data: [From]) -> [To] {
        return data.map { item in
            self.map(data: item)
        }
    }
    
    func reverseAll(data: [To]) -> [From] {
        return data.map { item in
            self.reverse(data: item)
        }
    }
}
