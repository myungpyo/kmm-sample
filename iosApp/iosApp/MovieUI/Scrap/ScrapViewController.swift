//
//  MainViewController.swift
//  iosApp
//
//  Created by myungpyo on 2020/12/23.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation
import UIKit
import shared
import Swinject

class ScrapViewController: UITableViewController {
    
    private let viewModel = MovieViewModel()
    private var disposeBag = CombineDisposeBag()
    
    private var tableViewAdapter: MovieTableViewAdapter?
    
    //MARK: - lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setupViews()
        self.observeMovies()
        viewModel.loadScrapMovies()
    }
    
    deinit {
        disposeBag.dispose()
    }
    
    //MARK: - private func
    private func setupViews() {
        self.tableViewAdapter = MovieTableViewAdapter(with: self)
        let nib = UINib(nibName: "MovieCell", bundle: Bundle.main)
        self.tableView.register(nib, forCellReuseIdentifier: "MovieCell")
        self.tableView.installAdapter(adapter: tableViewAdapter)
    }
    
    private func observeMovies() {
        
        viewModel.$movieViewData.sink { movieViewData in
            switch movieViewData {
            case .empty:
                NSLog("empty")
            case .inProgress:
                NSLog("inProgress")
            case .success(let movies):
                NSLog("success")
                self.tableViewAdapter?.bind(items: movies)
                self.tableView.reloadData()
            case .fail(let error):
                NSLog("fail \(error)")
            }
        }.store(in: disposeBag)
    }
}


extension ScrapViewController: MovieTableViewAdapterDelegate {
    
    func movieTableViewAdapter(_ adapter: MovieTableViewAdapter, toggleScrapOf movie: Movie) {
        self.viewModel.updateScrop(id: movie.id, scrap: !movie.scrap)
    }
}
